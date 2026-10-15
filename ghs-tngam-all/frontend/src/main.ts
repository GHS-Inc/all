import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { Component, Injectable, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

interface Asset {
  id: string; assetTag: string; name: string; category: string;
  status: string; assignedTo?: string; location?: string;
}
interface PageResult { content: Asset[]; totalElements: number; totalPages: number; number: number; }

@Injectable({ providedIn: 'root' })
class AssetApi {
  private http = inject(HttpClient);
  list() { return this.http.get<PageResult>('/api/assets?page=0&size=100'); }
  create(data: Partial<Asset>) { return this.http.post<Asset>('/api/assets', data); }
  search(q: string) { return this.http.get<Asset[]>('/api/assets/search', { params: { q } }); }
}

@Component({
  selector: 'ghs-root',
  standalone: true,
  imports: [FormsModule],
  template: `
  <header><div><span class="brand">GHS</span><span class="brand-sub">INC · ASSET MANAGEMENT</span></div>
    <span class="pill">Inventory workspace</span></header>
  <main>
    <section class="hero"><div><p class="eyebrow">ASSET OPERATIONS</p><h1>Asset inventory</h1>
      <p class="muted">Track company equipment, ownership and lifecycle status in one place.</p></div>
      <button class="primary" (click)="showForm.update(v => !v)">＋ Register asset</button></section>
    <section class="stats">
      <article><span>Total assets</span><strong>{{ assets().length }}</strong></article>
      <article><span>Available</span><strong>{{ count('AVAILABLE') }}</strong></article>
      <article><span>Assigned</span><strong>{{ count('ASSIGNED') }}</strong></article>
      <article><span>Maintenance / retired</span><strong>{{ count('UNDER_MAINTENANCE') + count('RETIRED') }}</strong></article>
    </section>
    @if (showForm()) {
      <section class="panel"><h2>Register an asset</h2>
        <div class="form-grid">
          <label>Asset tag<input [(ngModel)]="form.assetTag" placeholder="GHS-LAP-0001"></label>
          <label>Asset name<input [(ngModel)]="form.name" placeholder="Developer Laptop"></label>
          <label>Category<input [(ngModel)]="form.category" placeholder="LAPTOP"></label>
          <label>Serial number<input [(ngModel)]="form.serialNumber" placeholder="Serial number"></label>
          <label>Location<input [(ngModel)]="form.location" placeholder="Chennai"></label>
          <label>Manufacturer<input [(ngModel)]="form.manufacturer" placeholder="Manufacturer"></label>
        </div>
        <div class="actions"><button (click)="showForm.set(false)" class="secondary">Cancel</button><button class="primary" (click)="create()">Save asset</button></div>
      </section>
    }
    <section class="panel">
      <div class="toolbar"><div><h2>Assets</h2><p class="muted">Inventory records from the asset service</p></div>
        <div class="search"><input [(ngModel)]="query" placeholder="Search indexed assets"><button class="secondary" (click)="search()">Search</button><button class="secondary" (click)="load()">All assets</button></div>
      </div>
      @if (message()) { <p class="message">{{ message() }}</p> }
      <div class="table-wrap"><table><thead><tr><th>Asset tag</th><th>Name</th><th>Category</th><th>Location</th><th>Assigned to</th><th>Status</th></tr></thead>
      <tbody>
        @for (a of assets(); track a.id) {
          <tr><td class="tag">{{a.assetTag}}</td><td>{{a.name}}</td><td>{{a.category}}</td><td>{{a.location || '—'}}</td><td>{{a.assignedTo || '—'}}</td>
          <td><span class="status" [class.assigned]="a.status === 'ASSIGNED'" [class.retired]="a.status === 'RETIRED'">{{a.status}}</span></td></tr>
        }
        @empty { <tr><td colspan="6" class="empty">No assets found. Register the first asset to get started.</td></tr> }
      </tbody></table></div>
    </section>
    <footer>GHS Inc · Asset Management · Secure your inventory lifecycle</footer>
  </main>`
})
class AppComponent {
  private api = inject(AssetApi);
  assets = signal<Asset[]>([]);
  showForm = signal(false);
  message = signal('');
  query = '';
  form: any = { assetTag: '', name: '', category: '', serialNumber: '', location: '', manufacturer: '' };

  constructor() { this.load(); }
  load() {
    this.api.list().subscribe({ next: p => { this.assets.set(p.content); this.message.set(''); },
      error: () => this.message.set('Unable to load assets. Confirm the backend and infrastructure are running.') });
  }
  count(status: string) { return this.assets().filter(a => a.status === status).length; }
  create() {
    if (!this.form.assetTag || !this.form.name || !this.form.category) {
      this.message.set('Asset tag, name and category are required.'); return;
    }
    this.api.create(this.form).subscribe({
      next: () => { this.message.set('Asset registered successfully.'); this.form = { assetTag: '', name: '', category: '', serialNumber: '', location: '', manufacturer: '' }; this.showForm.set(false); this.load(); },
      error: e => this.message.set(e?.error?.detail || e?.error?.message || 'Could not register asset. Check the asset tag and API.')
    });
  }
  search() {
    if (!this.query.trim()) { this.load(); return; }
    this.api.search(this.query.trim()).subscribe({
      next: a => this.assets.set(a),
      error: () => this.message.set('Search index is not ready or the search request failed.')
    });
  }
}
bootstrapApplication(AppComponent, { providers: [provideHttpClient()] }).catch(console.error);
